rule(1,planner(PlanSet),
	and([planner(test),
	call_p(consult('planner/domain/rocket.pl')),
	call_p(plan([at(a, london), at(rocket1, london), has_fuel(rocket1)],[at(a,paris)],PlanSet))]),1,1).
