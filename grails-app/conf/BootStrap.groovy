import grails.util.Environment
import gdk48.*

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            new Gist(gistNo:1261979).save(flush:true, failOnError:true)
            new Gist(gistNo:1129161).save(flush:true, failOnError:true)
            new Gist(gistNo:1059105).save(flush:true, failOnError:true)
            new Gist(gistNo:1031115).save(flush:true, failOnError:true)
            new Gist(gistNo:1020384).save(flush:true, failOnError:true)
            new Gist(gistNo:1020286).save(flush:true, failOnError:true)
            new Gist(gistNo:1015558).save(flush:true, failOnError:true)
            new Gist(gistNo:1012403).save(flush:true, failOnError:true)
        }
    }

    def destroy = {
    }
}
