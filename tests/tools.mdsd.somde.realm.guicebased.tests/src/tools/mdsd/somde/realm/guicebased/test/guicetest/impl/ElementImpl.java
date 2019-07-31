/**
 */
package tools.mdsd.somde.realm.guicebased.test.guicetest.impl;

import javax.inject.Inject;
import tools.mdsd.somde.realm.guicebased.MockService;

public class ElementImpl extends ElementImplGen {

    @Inject
    MockService mockService;

    @Override
    public void callFoo() {
        mockService.foo();
    }


} // ElementImpl
