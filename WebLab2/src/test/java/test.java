import java.time.*;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        OffsetDateTime offsetdatetime
                = OffsetDateTime.now(ZoneId.of("+3"));

        String[] date=offsetdatetime.toString().split("T");
        date[1]=date[1].substring(0,8);
       System.out.println(date[1]);
        System.out.println(-180*(-1)/60);


    }
}
