package slackBoltServer.java.hello.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"slackBoltServer.java.hello.Application"})
public class SlashjiraApplication {
//https://mattermostpriya006.herokuapp.com/plugins/jira/api/v2/webhook?secret=kz6Onaz3VIYuFk2RG9nQwsDV28tQxrp7 request url from mattermos
	public static void main(String[] args) {
		SpringApplication.run(SlashjiraApplication.class, args);
	}
}
