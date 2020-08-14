package slackSlashCommandTrigger.io;

import com.atlassian.oauth.client.example.JiraOAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import slackSlashCommandTrigger.messages.Response;

@Component(value = "SlashJira")
public class Options {

    @Value("${jira.url}")
    String url;

    @Autowired
    JiraOAuthClient jiraService;

    public static String errorMessage = ":wave: Need some help with `/jira`?\n" +
            "Use `/jira` to lookup a jira ticket.  e.g. /jira PROJECT-123";

    private String appUrl;

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Response responseMessage(String ticket) {

        Response response = new Response();
      /*  try {
            Issue issue = jiraService.getIssue(ticket);
            String assignee = issue.getAssignee() != null ? issue.getAssignee().getName() : "Unassigned";
            String jiraMessage = "["+issue.getSummary()+"]("+url+"/browse/"+ticket+")\n" +
                "*Assignee* " + assignee +"\n" +
                "*Priority* "+issue.getPriority().getName() + "\n" +
                issue.getDescription();
            Attachment attachment = new Attachment();
            attachment.setText(jiraMessage);
            response.setAttachments(Arrays.asList(attachment));
            response.setText("##### ["+ticket+"]("+url+"/browse/"+ticket+")");
            response.setResponseType(Response.ResponseType.IN_CHANNEL);
        } catch( Exception e ) {
            response.setText(Options.errorMessage);
        }*/
        response.setText(Options.errorMessage);
        return response;
    }


}
