package gdk48

class GistController {

    def springSecurityService

    def list = {
        springSecurityService.currentUser
        def gists = Gist.list(max:5, offset:(params.offset?:'0').toLong(), sort:'gistNo', order:'desc').collect { gist ->
            def voted
            if (springSecurityService.currentUser) {
                voted = !!Vote.findByVoterAndGist(springSecurityService.currentUser, gist)
            }
            [gistNo:gist.gistNo, voted:voted, votedCount:Vote.countByGist(gist)]
        }
        [gists:gists]
    }

    def ranking = {
        // FIXME
        [votes: Vote.list().groupBy { it.gist }.values().sort {it.size()}.reverse()]
    }
}
