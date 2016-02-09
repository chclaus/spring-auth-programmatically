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

import de.chclaus.examples.ExampleUserDetailsService;
import de.chclaus.examples.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.Slf4JLoggingSystem;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chclaus (ch.claus@me.com)
 */
@Controller
public class AuthController {

  private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private ExampleUserDetailsService userDetailsService;

  @Autowired
  private UserService userService;

  @RequestMapping("/auth")
  public String auth(@RequestParam String token) {
    // This should be a service method...
    String usernameByToken = userService.findUsernameByToken(token);
    UserDetails userDetails = userDetailsService.loadUserByUsername(usernameByToken);

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authToken);

    LOG.info("User logged in. username={}, token={}", userDetails.getUsername(), token);

    return "redirect:/secured";
  }
}
