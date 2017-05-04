(ns cljpowershell.core
  (:require [schema.core :as s])
  (:import [com.profesorfalken.jpowershell
            PowerShell
            PowerShellResponse]))

(s/defn ^:always-validate invoke-command :- String
  "Executes given command in powershell and returns the output as string"
  (
    [command :- String]
    (let [response (PowerShell/executeSingleCommand command)
        output (. response getCommandOutput)]
    output))
  (
    [powershell :- PowerShell
    command :- String]
    (let [response (. powershell executeCommand command)
          output (. response getCommandOutput)]
    output)))

(defmacro with-ps-session
  "Executes given set of commands on machine
  as a single powershell session
  and closes the session in the end"
  [commands]
  `(let [ps-session# (PowerShell/openSession)
         ret# (doall (map (fn [command#]
                            (let [fnret# (invoke-command ps-session# command#)] fnret#))
                          (vec (~@commands))))]
     (when-not (= ps-session# nil)
       (. ps-session# close))
     ret#))

(defmacro on-many
  "Takes a list of hosts, executes body on each host in parallel, and returns a
  map of hosts to return values."
  [hosts & body]
  `(let [hosts# ~hosts]
     (->> hosts#
          (map #(future (on % ~@body)))
          doall
          (map deref)
          (map vector hosts#)
          (into {}))))

(defn on
  "Executes given set of commands the given host machine
  as a PowerShell session"
  [host & commands]
  (let [enter-ps-session-command (str "Enter-PSSession -ComputerName " host)
        exit (list "exit" "exit")
        full-command (conj commands enter-ps-session-command)
        full-command (conj exit full-command)
        result (with-ps-session (flatten full-command))]
    (butlast (butlast (rest result)))))