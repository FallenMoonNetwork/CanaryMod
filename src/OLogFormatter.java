import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class OLogFormatter extends Formatter { // CanaryMod: include for synthetic constructor

    private SimpleDateFormat b;

    final OLogAgent a;

    private OLogFormatter(OLogAgent ologagent) {
        this.a = ologagent;
        this.b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public String format(LogRecord logrecord) {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append(this.b.format(Long.valueOf(logrecord.getMillis())));
        if (OLogAgent.a(this.a) != null) {
            stringbuilder.append(OLogAgent.a(this.a));
        }

        stringbuilder.append(" [").append(logrecord.getLevel().getName()).append("] ");
        stringbuilder.append(this.formatMessage(logrecord));
        stringbuilder.append('\n');
        Throwable throwable = logrecord.getThrown();

        if (throwable != null) {
            StringWriter stringwriter = new StringWriter();

            throwable.printStackTrace(new PrintWriter(stringwriter));
            stringbuilder.append(stringwriter.toString());
        }

        return stringbuilder.toString();
    }

    OLogFormatter(OLogAgent ologagent, OLogAgentINNER1 ologagentinner1) {
        this(ologagent);
    }
}
