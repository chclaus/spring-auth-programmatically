/*
 * Copyright 2016, Christian Claus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.chclaus.examples.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chclaus (ch.claus@me.com)
 */
@Controller
public class ExampleController {

  @RequestMapping("/secured")
  @ResponseBody
  public String securedContent() {
    return "whooow secured content!!";
  }

  @RequestMapping("/")
  @ResponseBody
  public String index() {
    return "it works!";
  }
}
