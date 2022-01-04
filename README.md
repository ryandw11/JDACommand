# JDACommand
JDACommand is a very simple command library for JDA (Java Discord API). (This is not for slash commands.)
## Implementation
### Maven
![Maven Badge](https://www.ryandw11.com/api/repo-badge/maven-releases/me.ryandw11/JDACommand)  
  
Dependency:
```xml
<dependency>
    <groupId>me.ryandw11</groupId>
    <artifactId>JDACommand</artifactId>
    <version>1.2.0</version>
</dependency>
```
Repository:
```xml
<repository>
    <id>Ryandw11</id>
    <url>https://repo.ryandw11.com/repository/maven-releases/</url>
</repository>
```

## Usage
Using JDACommand is very simple.  
1) Create your commands and organize them into classes.
```java
import me.ryandw11.jdacommand.Command;
import me.ryandw11.jdacommand.JDACommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;

public class ExampleCommands {
    @Command(alias = {"ping", "par", "works"})
    public void hi(JDACommand cmd) {
        MessageChannel chan = cmd.getChannel();
        chan.sendMessage("pong!").queue();
    }

    @Command(alias = {"deleteme"})
    public void deleteMsg(JDACommand cmd) {
        MessageChannel chan = cmd.getChannel();
        cmd.getMessage().delete().queue();
        chan.sendMessage("Your message has been deleted!").queue();
    }
}
```
2) Create `JDACommandHandler` in your main and register your command classes to it.
```java
public class MainBot {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault(/* tokey */)
                .build();
        JDACommandHandler jdacmd = new JDACommandHandler(jda, "!");
        
        jdacmd.register(new ExampleCommand());
        
        jdacmd.finalizeCommands();
    }
} 
```
