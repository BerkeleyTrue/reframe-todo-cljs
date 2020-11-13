(defproject todo "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library
                               org.clojure/google-closure-library-third-party]]
                 ;; client
                 [thheller/shadow-cljs "2.11.0"]
                 [reagent "0.10.0"]
                 [re-frame "1.1.1"]

                 ;; server
                 [metosin/reitit "0.5.10"]
                 [metosin/reitit-ring "0.5.10"]]

  :plugins [[lein-shadow "0.2.2"]
            [lein-tailwind "0.1.2"]
            [cider/cider-nrepl "0.25.3"]
            [lein-shell "0.5.0"]
            [lein-ring "0.12.5"]
            [lein-pdo "0.1.1"]]

  :min-lein-version "2.9.0"

  :source-paths ["src/server" "src/client"]

  ; what to clean up on lein clean
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "resources/public/css" "target"]

  :tailwind {:tailwind-dir "src/css/tailwind"
             :output-dir   "resources/public/css"
             :styles [{:src "main.css"
                       :dst "main.css"}]}

  :ring {:handler todo.core/app
         :port 8129
         :auto-reload? true}

  :npm-dev-deps {"shadow-cljs" "2.11.0"}

  :shadow-cljs {:nrepl {:port 8777}

                :dependencies [[cider/cider-nrepl "0.25.3"]]

                :builds {:app {:target :browser
                               :output-dir "resources/public/js/compiled"
                               :asset-path "/js/compiled"
                               :modules {:app {:init-fn todo.core/init
                                               :preloads [devtools.preload]}}

                               :devtools {:http-root "resources/public"
                                          :http-port 8128}}}}

  
  :shell {:commands {"karma" {:windows         ["cmd" "/c" "karma"]
                              :default-command "karma"}
                     "open"  {:windows         ["cmd" "/c" "start"]
                              :macosx          "open"
                              :linux           "xdg-open"}}}

  :aliases {
            "css"          ["tailwind" "build"]

            "client"       ["shadow" "watch" "app"]

            "server"       ["ring" "server-headless"]

            "dev"          ["with-profile" "dev" "pdo"
                            ["css"]
                            ["server"]
                            ["client"]]

            "prod"         ["do"
                            ["shell" "echo" "\"DEPRECATED: Please use lein release instead.\""]
                            ["release"]]

            "release"      ["with-profile" "prod" "do"
                            ["shadow" "release" "app"]]

            "build-report" ["with-profile" "prod" "do"
                            ["shadow" "run" "shadow.cljs.build-report" "app" "target/build-report.html"]
                            ["shell" "open" "target/build-report.html"]]

            "karma"        ["do"
                            ["shell" "echo" "\"DEPRECATED: Please use lein ci instead.\""]
                            ["ci"]]
            "ci"           ["with-profile" "prod" "do"
                            ["shadow" "compile" "karma-test"]
                            ["shell" "karma" "start" "--single-run" "--reporters" "junit,dots"]]}

  :profiles
    {:dev
      {:dependencies [[binaryage/devtools "1.0.2"]]
       :source-paths ["dev"]}

     :prod {}}

  :prep-tasks [])
