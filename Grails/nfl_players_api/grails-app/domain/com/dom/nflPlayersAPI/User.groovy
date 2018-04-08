package com.dom.nflPlayersAPI;

import grails.rest.Resource;

@Resource(uri='/users', formats=['json'])
class User {
    String username;
    String firstname;
    String lastname;
    String password;
    Integer age;

    static constraints = {
      username blank: false;
      firstname blank : false;
      lastname blank : false;
      password blank : false;
      age blank : false;
    }
}
