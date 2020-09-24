(ns todo.db)

(def default-db
  {
   :title "Todo App"
   :todos [{:title "Do CET"
            :is-completed? true}

           {:title "Do snapraid build"
            :is-completed? false}]})
