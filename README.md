BPMN: src/main/resources/cost_compensation_process.bpmn

- http://localhost:8081/
- Имя и пароль "demo"
- В Camunda TaskList запустить процесс "CostCompensationProcess"
- Ввести "Compensation amount" и "Personnel Number". Если нет такого работника, он создается
- Процесс оперирует следующими данными: номер счета и идентификатор работника, сумма возмещения, состояние подтверждения возмещения
