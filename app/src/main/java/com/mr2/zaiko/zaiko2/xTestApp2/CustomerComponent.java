package com.mr2.zaiko.zaiko2.xTestApp2;

import dagger.Subcomponent;

/**
 * Created by ryosuke on 2018/02/14.
 */

@CustomerScope
@Subcomponent
public interface CustomerComponent {

    Customer enter();

    @Subcomponent.Builder
    interface Builder{
        CustomerComponent build();
    }
}
