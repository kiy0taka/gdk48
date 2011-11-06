package gdk48

import grails.test.*

class VoteTests extends GrailsUnitTestCase {
    void testSave() {
        mockDomain(Vote)
        def user = new User(username:'user', password:'pass')
        def gist = new Gist(gistNo:1)
        def vote = new Vote(voter:user, gist:gist)
        vote.save()
        assert vote.id
        assert vote.voter
        assert vote.gist
    }

    void testUnique() {
        def user = new User(username:'user', password:'pass')
        def gist = new Gist(gistNo:1)
        mockForConstraintsTests(Vote, [new Vote(voter:user, gist:gist)])

        assert new Vote(voter:user, gist:gist).validate() == false
    }
}
