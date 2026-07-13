import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

record ServiceResponse(String name, int price) {
}

class HolidayPackageService {
    public void buildHolidayPackage() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        try {

            long time = System.currentTimeMillis();

            Future<ServiceResponse> hotelFuture = es.submit(() -> callMicroservice("Hotel", 200, 3000));
            Future<ServiceResponse> activityFuture = es.submit(() -> callMicroservice("Activity", 100, 2000));
            Future<ServiceResponse> flightFuture = es.submit(() -> callMicroservice("Flight", 150, 7000));

            List<Future<ServiceResponse>> futureList = List.of(hotelFuture, activityFuture, flightFuture);
            // Static work
            Thread.sleep(2000);

            int total = 0;

            for (Future<ServiceResponse> future : futureList) {
                try {
                    ServiceResponse res = future.get(3, TimeUnit.SECONDS);
                    total += res.price();
                    System.out.printf("%s price %s \n", res.name(), res.price());
                } catch (Exception e) {
                    System.err.println("One Service Failed");
                }
            }

            time = System.currentTimeMillis() - time;

            System.out.println("----------------");
            System.out.println("Total : " + total);
            System.out.println("Time : " + time);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
        } finally {
            es.shutdown();
        }
    }

    private ServiceResponse callMicroservice(String name, int price, int delay) throws InterruptedException {
        Thread.sleep(delay);
        return new ServiceResponse(name, price);
    }
}

class HolidayPackage {
    public void buildHolidayPackage() {
        new HolidayPackageService().buildHolidayPackage();
    }
}
