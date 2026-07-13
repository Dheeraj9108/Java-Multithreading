record ServiceResponse(String name, int price){}

class HolidayPackageService {

    public void buildHolidayPackage() {

        int total = 0;
        try {
            long time = System.currentTimeMillis();

            ServiceResponse hotelServiceResponse = callMicroservice("Hotel", 200, 3000);
            total+=hotelServiceResponse.price();
            System.out.printf("%s Price is %s \n",hotelServiceResponse.name(),hotelServiceResponse.price());
        
            ServiceResponse flightServiceResponse = callMicroservice("Flight", 100, 2000);
            total+=flightServiceResponse.price();
            System.out.printf("%s Price is %s \n",flightServiceResponse.name(),flightServiceResponse.price());
        
            ServiceResponse activityServiceResponse = callMicroservice("Activity", 250, 1000);
            total+=activityServiceResponse.price();
            System.out.printf("%s Price is %s \n",activityServiceResponse.name(),activityServiceResponse.price());
            
            // Static work
            Thread.sleep(2000);

            time = System.currentTimeMillis() - time;

            System.out.println("----------------");
            System.out.println("Total : " + total);
            System.out.println("Time : " + time);

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public ServiceResponse callMicroservice(String name, int price, int delay) throws InterruptedException {
        Thread.sleep(delay);
        return new ServiceResponse(name, price);
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        // new HolidayPackageService().buildHolidayPackage();
        new HolidayPackage().buildHolidayPackage();
    }
}
