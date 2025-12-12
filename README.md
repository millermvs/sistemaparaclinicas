# 🏥 Sistema de Agendamento de Consultas – Backend

API REST para gestão de **clínicas, médicos, pacientes e consultas**, incluindo **agendamento, remarcação e cancelamento de consultas**.  
Projeto desenvolvido em **Java 21 + Spring Boot 3**, pensado para evoluir para um modelo **SaaS para clínicas**.

A ideia é representar de forma clara os relacionamentos entre:

- **Clínica**
- **Médicos**
- **Pacientes**
- **Consultas (entidade rica entre Médico e Paciente)**

---

## 🎯 Objetivo inicial do Projeto

Praticar:

- Modelagem **conceitual (MER)** e **lógica (DER)**  
- Identificação de **entidades ricas**  
- Relacionamentos **1:N** e **N:N via entidade de associação**  
- Pensar o banco já preparado para um backend futuro (Java / Spring Boot)

---

## 🧱 Modelagem Conceitual (Resumo)

### Entidades principais

- **Clínica**
  - Uma clínica possui vários médicos.

- **Médico**
  - Pertence a uma clínica.
  - Pode atender **muitas consultas**.

- **Paciente**
  - Pode realizar **muitas consultas** ao longo do tempo.

- **Consulta** (entidade rica)
  - Relaciona **um médico** e **um paciente**.
  - Guarda informações próprias:
    - data
    - hora
    - tipo (retorno, primeira vez, emergência, etc.)
    - observações (opcional)
  - Cada consulta pertence a **1 médico** e **1 paciente**.

---

## 🔗 Relacionamentos

- **Clínica 1 — N Médicos**
- **Médico 1 — N Consultas**
- **Paciente 1 — N Consultas**

A entidade **Consulta** funciona como uma **entidade de associação rica** entre **Médico** e **Paciente**:

- Em vez de um relacionamento N:N direto entre Médico e Paciente,
- usamos **Consulta** para guardar os dados importantes do agendamento.

Isso permite responder perguntas como:

- Quais consultas o paciente *X* já realizou?
- Qual a agenda de consultas do médico *Y* em um determinado dia?
- Quantas consultas de um determinado tipo foram feitas no mês?

---

## 🗂 Exemplo de atributos (versão simplificada)

**Clínica**
- `id_clinica`
- `nome`
- `cnpj`
- `endereco`

**Médico**
- `id_medico`
- `nome`
- `crm`
- `especialidade`
- `id_clinica` (FK)

**Paciente**
- `id_paciente`
- `nome`
- `cpf`
- `data_nascimento`
- `telefone`

**Consulta**
- `id_consulta`
- `data`
- `hora`
- `tipo`
- `observacoes`
- `id_medico` (FK)
- `id_paciente` (FK)

---

## 📌 Decisões de Modelagem

- A tabela **CONSULTA** foi modelada como **entidade rica**, e não apenas uma tabela de junção:
  - Porque ela tem **informações próprias** (data, hora, tipo, etc.)
  - E porque queremos histórico detalhado de atendimentos.
- Evitamos relacionamento N:N direto entre **Médico** e **Paciente**:
  - Toda relação entre os dois acontece **por meio de uma consulta**.
- A **Clínica** foi mantida como raiz simples:
  - Apenas para agrupar médicos e, futuramente, facilitar filtros por unidade.

---

## 🚀 Próximos Passos (ideias de evolução)

- Implementar uma API REST em **Java + Spring Boot** para:
  - Cadastrar pacientes, médicos e consultas.
  - Listar agenda de um médico por dia.
  - Listar histórico de consultas de um paciente.
- Criar um front-end simples (Angular ou outra stack) para consumir essa API.

---

## 👨‍💻 Autor

**Miller Vieira dos Santos**  
Desenvolvedor em formação, focado em **Java, Spring Boot, JPA, modelagem de domínio e boas práticas**.  
- LinkedIn: [linkedin.com/in/millemvs](https://www.linkedin.com/in/millemvs)  
- GitHub: [github.com/millermvs](https://github.com/millermvs)
