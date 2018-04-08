package com.dom.nflPlayersAPI;

import grails.rest.Resource;

@Resource(uri='/qb', formats=['json'])
class QB {
    Integer season;
    String team;
    Integer number;
    String name;
    String position;
    Integer played;
    Integer passingAttempts;
    Integer passingCompletions;
    Integer passingYards;
    Integer passingCompletionPercentage;
    Integer passingYardsPerAttempt;
    Integer passingYardsPerCompletion;
    Integer passingTouchdowns;
    Integer passingIntegererceptions;
    Integer passingRating;
    Integer passingLong;
    Integer passingSacks;
    Integer passingSackYards;
    Integer rushingAttempts;
    Integer rushingYards;
    Integer rushingYardsPerAttempt;
    Integer rushingTouchdowns;
    Integer rushingLong;
    Integer fantasyPoints;
    Integer fumbles;
    Integer teamID;

    static constraints = {
      season blank: false;
      team blank: false;
      number blank : false;
      name blank : false;
      position blank : false;
      played blank : false;
      passingAttempts blank : false;
      passingCompletions blank : false;
      passingYards blank : false;
      passingCompletionPercentage blank : false;
      passingYardsPerAttempt blank : false;
      passingYardsPerCompletion blank : false;
      passingTouchdowns blank : false;
      passingIntegererceptions blank : false;
      passingRating blank : false;
      passingLong blank : false;
      passingSacks blank : false;
      passingSackYards blank : false;
      rushingAttempts blank : false;
      rushingYards blank : false;
      rushingYardsPerAttempt blank : false;
      rushingTouchdowns blank : false;
      rushingLong blank : false;
      fantasyPoints blank : false;
      fumbles blank : false;
      teamID blank : false;
    }
}
