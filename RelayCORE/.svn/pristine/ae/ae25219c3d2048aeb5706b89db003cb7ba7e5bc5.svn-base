:- prolog_flag(unknown,_,fail).


askable(device_dead,'Does the device refuse to do anything').
askable(knobs_do_something,'Does changing the switch positions or tuning the knobs change anything').
askable(lights_out,'Do all the lights in the house seem to be off').
askable(cord_frayed,'Does the outer covering of the cord appear to be coming apart').
askable(handyperson,'Are you good at fixing things').
askable(familiar_appliance,'Are you familiar with how this appliance works').
askable(device_on,'Is the ON/OFF switch set to ON').
askable(just_plugged,'Did you just plug the appliance in').
askable(in_socket,'Is the cord firmly plugged into the socket').
askable(smell_smoke,'Do you smell smoke').
askable(liquids,'Have any liquids spilled on the appliance just now').
askable(heats,'Does the appliance heat things').
askable(powerful,'Does the appliance require a lot of power').
askable(has(X),['Does the appliance have ',X]).
askable(hear(X),['Did you hear a ',X]).

/* Top-level diagnosis rules */

rule(fuse1,diagnosis('Fuse blown'),and([power_problem, lights_out]),1,1).
rule(fuse2,diagnosis('Fuse blown'),and([power_problem, hear(pop)]),1,1).
rule(cord1,diagnosis('Break in cord'),and([power_problem, cord_frayed]),1,1).
rule(cord2,diagnosis('Short in cord'),and([diagnosis('Fuse blown'), cord_frayed]),1,1).
rule(on1,diagnosis('Device not turned on'),and([power_problem, klutz_user, has('an on-off switch or control'), no(device_on)]),1,1).
rule(on2,diagnosis('Cord not in socket properly'),and([power_problem, klutz_user, just_plugged, no(in_socket)]),1,1).
rule(boh2,diagnosis('Foreign matter caught on heating element'),and([heating_element, no(power_problem), smell_smoke]),1,1).
rule(boh3,diagnosis('Appliance wet(emdry it out and try again'),and([power_poblem, klutz_user, liquids]),1,1).
rule(boh4,diagnosis('Controls adjusted improperly'),and([klutz_user, has('knobs or switches')]),1,1).
rule(mech1,diagnosis('Kick it, then try it again'),and([mechanical_problem]),1,1).
rule(boh1,diagnosis('Throw it out and get a new one'),and([]),1,1).

/* Definitions of intermediate predicates */
rule(pp1,power_problem,and([device_dead]),1,1).
rule(pp2,power_problem,and([has('knobs or switches'), no(knobs_do_something)]),1,1).
rule(pp3,power_problem,and([smell_smoke, no(heating_element)]),1,1).
rule(ku1,klutz_user,and([no(handyperson)]),1,1).
rule(ku1,klutz_user,and([no(familiar_appliance)]),1,1).
rule(m1,mechanical_problem,and([hear('weird noise'), has('moving parts')]),1,1).
rule(he1,heating_element,and([heats]),1,1).
rule(he2,heating_element,and([powerful]),1,1).





