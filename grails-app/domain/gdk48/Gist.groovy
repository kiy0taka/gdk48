package gdk48

class Gist {

    Long gistNo

    static constraints = {
        gistNo nullable:false, unique:true
    }
}
