package html;

import core.PageData;

public interface SetupIncludeTeardownI {
    void init(PageData pageData, boolean includeSuiteSetup) throws Exception;
    String render() throws Exception;
}
