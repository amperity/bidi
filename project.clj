;; Copyright © 2014, JUXT LTD.

(defproject bidi "2.1.6"
  :description "Bidirectional URI routing"
  :url "https://github.com/juxt/bidi"

  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}

;;  :pedantic? :abort

  :dependencies [[prismatic/schema "1.3.0"]]

  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-doo "0.1.6"]]

  :prep-tasks ["javac" "compile"]

  :profiles {:provided {:dependencies [[ring/ring-core "1.9.5" :exclusions [org.clojure/clojure]]]}
             :dev {:exclusions [[org.clojure/tools.reader]]
                   :resource-paths ["test-resources"]
                   ;;:global-vars {*warn-on-reflection* true}
                   :dependencies [[org.clojure/clojure "1.11.1"]
                                  [org.clojure/clojurescript "1.11.57"]
                                  [org.clojure/tools.reader "1.3.6"]
                                  [ring/ring-mock "0.3.0"]
                                  [compojure "1.7.0"]
                                  [criterium "0.4.6"]
                                  [org.mozilla/rhino "1.7.14"]]}}

  :aliases {"deploy" ["do" "clean," "deploy" "clojars"]
            "test-cljs" ["doo" "rhino" "test" "once"]}

  :jar-exclusions [#"\.swp|\.swo|\.DS_Store"]

  :lein-release {:deploy-via :shell
                 :shell ["lein" "deploy"]}

  :doo {:paths {:rhino "lein run -m org.mozilla.javascript.tools.shell.Main"}}

  :cljsbuild {:builds
              {:test {:source-paths ["src" "test"]
                      :compiler {:output-to "target/unit-test.js"
                                 :main 'bidi.runner
                                 :optimizations :whitespace}}}})
