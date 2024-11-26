### O
- Spring boot 3 layer architecture 
- Integration test to unit test
- Replace fake repository with JPA repository

### R
- I feel challenged to replace the fake repository with JPA repository

### I
- I encountered quick a lot of problems when I tried to replace the fake repository with JPA repository.
For example, somehow the ID comparison was not working properly. I struck with this problem for a long time.
Finally, I used '.withComparedFields' to specify other fields to compare. This solved the problem.
But I think this may not the best solution.

### D
- I will try to find a better solution to compare the ID field.
- Need more practice on JPA repository

