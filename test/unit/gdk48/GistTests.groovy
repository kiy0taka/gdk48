package gdk48

import grails.test.*

class GistTests extends GrailsUnitTestCase {

    void testUnique() {
        mockForConstraintsTests(Gist, [new Gist(gistNo:1)])
        assert new Gist(gistNo:1).validate() == false
    }
}
