package gdk48

class Vote {

    Gist gist
    User voter

    static constraints = {
        gist nullable:false, unique:'voter'
        voter nullable:false
    }
}
