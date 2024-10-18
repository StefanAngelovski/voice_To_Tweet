package voiceToTweet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@SpringBootApplication
public class VoiceToTweetApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoiceToTweetApplication.class, args);
	}

	private String consumerKey = System.getenv("TWITTER_CONSUMER_KEY");
	private String consumerSecret = System.getenv("TWITTER_CONSUMER_SECRET");

	@Bean
	public Twitter twitter() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		return tf.getInstance();
	}
}
