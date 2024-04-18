# Ofitoo - backend
oFitoo is an innovative mobile application designed to help you take care of your health , monitor your nutrition   
and achieve goals related to diet and physical activity.   
The application combines the best features of popular solutions such as MyFitnessPal and Fitatu, while offering   
simplicity and the ability to disconnect modules that are unnecessary for us.  


### The main features of oFitoo are:  

**Calorie Intake Tracker:** Monitor your daily calorie, macronutrient and micronutrient intake to stay on top of your diet.  
**Meal Planning:** Create personalized eating plans tailored to your goals and preferences.  
**Barcode Scanning:** Add foods to your food diary quickly and easily by scanning their barcodes.  
**Statistics and Charts:** Receive charts and statistics to help you monitor your progress and achieve your goals.  
**Social and Motivation:** Allow your partner to edit your meals, making it easier to track your meals and track your performance.  

![microservice-architecture.png](microservice-architecture.png)

## Directory Structure
The directory structure of the project is as follows including important files:

```yaml
├── microservices
│     ├── calorie-tracker
│     ├── habit-tracker 
│     ├── notification
│     ├── product
│     ├── user
│
│
├── .github
│     ├── workflows # Git Actions CI/CD files
│     ├── ISSUE_TEMPLATE # Git Actions issue form templates
│
│
├── scripts
      ├── docker-compose.yml
```

## How to build and run
Prerequisites
- java 21 (graalvm-ce-21)
- maven
- podman or docker

### Clone the Repository
```shell
git clone https://github.com/Progress-House/ofitoo-api
```
### Run Docker Compose in order to set up a database (mongoDB)
it is very important to do this before building the project, spring data jpa (hibernate) maps entities to the database. 
If the database does not exist, it will throw an error.

```shell
cd ofitoo-api/scripts
docker-compose up
```

### Run Docker Compose (mongoDB)
```shell
cd ofitoo-api/microservices
mvn clean install
mvn spring-boot:run
```
