# Attention! 
This is not a fully completed project. It is being developed all the time. The current frontend is only demonstration and does not represent the entire project but a fraction of it.

# Description

A web application that allows you to register patients, check doctors, order tests and add results. It is possible to add test setups, doctors, workstations.

Additionally, the ability to create doctor boards and order cards on **Trello** has been added.
**Api Medic** was used to obtain symptoms and obtain a probable diagnosis based on them/

Web application that allows patients to register for a medical visit. Additionally, it enables entering the results, managing patient data, sending e-mails with notifications to doctors.

- Main: Java, Spring, SpringBoot, Hibernate
- Additional: SQL, Vaadin for design [basic form]
- For testing: JUnit4, Mockito, [Selenium]
- App builder: Gradle
- Version control: Git
- DataBases: MySQL, PostgreSQL for Heroku
- Patterns: builder for most of components, [observer for doctors - mail when test is resulted], singleton for repositories, [decorator for bill creation]
- Additionally: lombok, [thymeleaf]
- External Api: Trello - information about orders, new board for all doctors, ApiMedic - symptoms and diagnosis for visits

[In square brackets I mark elements not yet implemented]

# Endpoints
![E1](https://github.com/zagPatryk/DrMed/blob/master/info/EndPoints1.png)
![E2](https://github.com/zagPatryk/DrMed/blob/master/info/EndPoints2.png)
![E3](https://github.com/zagPatryk/DrMed/blob/master/info/EndPoints3.png)

# Frontend
![F1](https://github.com/zagPatryk/DrMed/blob/master/info/F1.png)
![F2](https://github.com/zagPatryk/DrMed/blob/master/info/F2.png)
![F3](https://github.com/zagPatryk/DrMed/blob/master/info/F3.png)
