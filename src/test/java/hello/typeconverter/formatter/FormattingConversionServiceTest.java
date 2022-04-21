package hello.typeconverter.formatter;

import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService(){
        DefaultFormattingConversionService conversion = new DefaultFormattingConversionService();
        //컨버터 등록
        conversion.addConverter(new StringToIpPortConverter());
        conversion.addConverter(new IpPortToStringConverter());
        //포맷터 등록
        conversion.addFormatter(new MyNumberFormatter());

        //컨버터 사용
        IpPort ipPort = conversion.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
        //포맷터 사용
        String result = conversion.convert(1000, String.class);
        assertThat(result).isEqualTo("1,000");
        assertThat(conversion.convert("1,000", Long.class)).isEqualTo(1000L);
    }
}
