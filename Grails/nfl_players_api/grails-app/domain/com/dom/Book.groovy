package com.dom

import grails.rest.Resource

@Resource(uri='/books', formats=['json'])
class Book {
    String title

    static constraints = {
      title blank: false
    }
}
