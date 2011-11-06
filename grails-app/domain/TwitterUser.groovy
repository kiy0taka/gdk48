import com.the6hours.grails.springsecurity.twitter.TwitterUserDomain
import gdk48.User

class TwitterUser implements TwitterUserDomain {

    int uid
    String screenName
    String tokenSecret
    String token

	static belongsTo = [user: User]

	static constraints = {
		uid unique: true
	}
}
