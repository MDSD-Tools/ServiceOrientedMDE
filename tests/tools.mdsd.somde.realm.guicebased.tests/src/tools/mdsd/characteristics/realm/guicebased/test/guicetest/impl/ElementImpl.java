/**
 */
package tools.mdsd.characteristics.realm.guicebased.test.guicetest.impl;

import javax.inject.Inject;
import tools.mdsd.characteristics.realm.guicebased.MockService;

public class ElementImpl extends ElementImplGen {

    @Inject
    MockService mockService;

    @Override
    public void callFoo() {
        mockService.foo();
    }


} // ElementImpl
