package anonimClass;

public class Animal implements Auto{
    public static void main(String[] args) {


        Auto auto = new Auto() { // это аноним класс

            @Override
            public void flyingMoto() {
                System.out.println("Fly Moto");
            }
        };
        auto.flyingMoto();

        //Анонимный класс - это классы, которые не имеют имени
        // и их создание происходит в момент инициализации объекта.

    }

    @Override
    public void flyingMoto() {

    }
}
