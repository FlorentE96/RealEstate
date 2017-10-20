// TODO: write JavaDoc
// TODO: check implementation
public class Agent extends Person {
    private double salary;
    private double salesBalance;

    public Agent(String _name, int _ID, double _salary)
    {
        super(_name,_ID);
        this.salary = _salary;
        this.salesBalance = 0.0;
    }

    // com vendas anteriores a criacao do funcionario
    // FIXME: JavaDoc syntax
    public Agent(String _name, int _ID, double _salary, double _salesBalance)
    {
        super(_name,_ID);
        this.salary = _salary;
        this.salesBalance = _salesBalance;
    }

    // GETTERS E SETTERS
    public double getSalary()
    {
        return salary;
    }

    public double getSalesBalance()
    {
        return salesBalance;
    }

    public void setSalary(double _salary)
    {
        this.salary = _salary;
    }

    public void setSalesBalance(double _salesBalance)
    {
        this.salesBalance = _salesBalance;

    }

    //updateSalary aumenta o salario do funcionario em 5% por padrao ou em aalguma PORCENTAGEM definicda por parametro
    public void updateSalary(double up)
    {
        this.salary = this.salary * (1 + up*0.01);
    }

    public void updateSalary()
    {
        this.salary = this.salary * 1.05;
    }

    //makeSale efetua a venda de um client para o outro
    public void makeSale(Client clientFrom, Client clientTo, Property IdPro)
    {
        //receber dinheiro clientTo
        //dar dinheiro para clientFrom
        //apagar propriedade do sistema
        //this.salesBalance = this.salesBalance + valorPropriedade;
    }

}
