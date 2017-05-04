(defproject cljpowershell "0.1.0-SNAPSHOT"
  :description "Clojure Library to Interact with Powershell"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :author "deepne@microsoft.com"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [prismatic/schema "1.1.5"]
                 [com.profesorfalken/jPowerShell "1.7"]]

  :jvm-opts ["-Xmx32g" "-XX:+UseConcMarkSweepGC" "-XX:+UseParNewGC"
             "-XX:+CMSParallelRemarkEnabled" "-XX:+AggressiveOpts"
             "-XX:+UseFastAccessorMethods" "-server"
             "-XX:-OmitStackTraceInFastThrow"])