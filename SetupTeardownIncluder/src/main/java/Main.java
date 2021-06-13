import core.PageData;
import html.SetupIncludeTeardownI;
import html.SetupIncludeTeardown;

public class Main {
    public static void main(String[] args){
        try {
//            SetupIncludeTeardownI setupIncludeTeardown = new HtmlUtil();
            SetupIncludeTeardownI setupIncludeTeardown = new SetupIncludeTeardown();
            setupIncludeTeardown.init(new PageData("PAGE CONTENT"), true);
            String html = setupIncludeTeardown.render();
            System.out.println(html);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
