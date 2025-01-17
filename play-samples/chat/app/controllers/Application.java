/*
 * Copyright 2008-2022 Async-IO.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.api.inject.ApplicationLifecycle;
import views.html.index;

import javax.inject.Inject;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.atmosphere.samples.play.Chat;
import org.atmosphere.play.AtmosphereCoordinator;

public class Application extends Controller {

    public Result index() {
        return ok(index.render());
    }

    @Inject
    public Application(ApplicationLifecycle lifecycle) {
        AtmosphereCoordinator.instance().discover(Chat.class).ready();

        lifecycle.addStopHook(new Callable<CompletableFuture<Object>>() {
            @Override
            public CompletableFuture<Object> call() throws Exception {
                AtmosphereCoordinator.instance().shutdown();
                return CompletableFuture.completedFuture(null);
            }
        });
    }
}