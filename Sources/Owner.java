// TODO: write JavaDoc
public class Owner extends Client
{
    private Property propertyOwned[];  //coloquei apenas o ID da propriedade para facilitar a programacao

    public Owner(String _name, int _ID)
    {
        super(_name, _ID);
    }

    /**
    * //adiciona uma propriedade ao dono apartir de um ID
    * //Este metodo é para ser usado dentro da classe propriedade quando for criar alguma propriedade no sistema
    */
    public void addPropertie(int ID)
    {
        //adicionar ID da propriedade ao owner
    }
}