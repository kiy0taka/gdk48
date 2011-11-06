package gdk48

class VoteController {

    def springSecurityService

    def ajaxVote = {
        def gistNo = params.long('gistNo')
        if (gistNo) {
            def gist = Gist.findByGistNo(gistNo)
            new Vote(voter:springSecurityService.currentUser, gist:gist).save(flush:true)
        }
        render text:'OK', status:200
    }

    def ajaxUnvote = {
        def gistNo = params.long('gistNo')
        if (gistNo) {
            def gist = Gist.findByGistNo(gistNo)
            Vote.findByVoterAndGist(springSecurityService.currentUser, gist)?.delete(flush:true)
        }
        render text:'OK', status:200
    }
}
