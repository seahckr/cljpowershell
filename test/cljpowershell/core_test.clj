(ns cljpowershell.core-test
  (:require [clojure.test :refer :all]
            [cljpowershell.core :refer :all]))

(deftest hostname-test
  (testing "Test Getting Hostname"
    (is (= "dev-seahckr\r\n"
           (first (on "dev-seahckr" "hostname"))))))

(deftest hostname-test-on-many
  (testing "Test Getting Hostname"
    (let [psresult (on-many ["dev-seahckr"] "hostname")]
      (is (= (first (get psresult "dev-seahckr")) "dev-seahckr\r\n")))))
