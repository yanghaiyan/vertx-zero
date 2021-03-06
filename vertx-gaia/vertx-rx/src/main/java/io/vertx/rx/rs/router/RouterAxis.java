package io.vertx.rx.rs.router;

import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import io.vertx.reactivex.ext.web.handler.CookieHandler;
import io.vertx.up.eon.Orders;
import io.vertx.up.rs.Axis;

public class RouterAxis implements Axis<Router> {

    @Override
    public void mount(final Router router) {
        // 1. Cookie, Body
        router.route().order(Orders.COOKIE)
                .handler(CookieHandler.create());
        router.route().order(Orders.BODY)
                .handler(BodyHandler.create());
        // 2. Session
    }
}
