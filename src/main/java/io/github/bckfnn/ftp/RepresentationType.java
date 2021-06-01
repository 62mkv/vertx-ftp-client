package io.github.bckfnn.ftp;

/**
 * REPRESENTATION TYPE (TYPE)
 *
 *             The argument specifies the representation type as described
 *             in the Section on Data Representation and Storage.  Several
 *             types take a second parameter.  The first parameter is
 *             denoted by a single Telnet character, as is the second
 *             Format parameter for ASCII and EBCDIC; the second parameter
 *             for local byte is a decimal integer to indicate Bytesize.
 *             The parameters are separated by a <code>SPACE</code> (Space, ASCII code
 *             32).
 *
 *             The following codes are assigned for type:
 *<pre><code>
 *                          \    /
 *                A - ASCII |    | N - Non-print
 *                          |----| T - Telnet format effectors
 *                E - EBCDIC|    | C - Carriage Control (ASA)
 *                          /    \
 *                I - Image
 *
 *                L (byte size) - Local byte Byte size
 *</code></pre>
 *             The default representation type is ASCII Non-print.  If the
 *             Format parameter is changed, and later just the first
 *             argument is changed, Format then returns to the Non-print
 *             default.
 *
 * Created by 62mkv on 31.05.2021
 */
public class RepresentationType {
    private final String type;
    private final String format;

    private RepresentationType(String type, String format) {
        this.type = type;
        this.format = format;
    }

    /**
     *  3.1.1.3.  IMAGE TYPE
     *
     *             The data are sent as contiguous bits which, for transfer,
     *             are packed into the 8-bit transfer bytes.  The receiving
     *             site must store the data as contiguous bits.  The structure
     *             of the storage system might necessitate the padding of the
     *             file (or of each record, for a record-structured file) to
     *             some convenient boundary (byte, word or block).  This
     *             padding, which must be all zeros, may occur only at the end
     *             of the file (or at the end of each record) and there must be
     *             a way of identifying the padding bits so that they may be
     *             stripped off if the file is retrieved.  The padding
     *             transformation should be well publicized to enable a user to
     *             process a file at the storage site.
     *
     *             Image type is intended for the efficient storage and
     *             retrieval of files and for the transfer of binary data.  It
     *             is recommended that this type be accepted by all FTP
     *             implementations.
     *
     */
    public static RepresentationType image() {
        return new RepresentationType("I", "");
    }

    /**
     * 3.1.1.4.  LOCAL TYPE
     *
     *             The data is transferred in logical bytes of the size
     *             specified by the obligatory second parameter, Byte size.
     *             The value of Byte size must be a decimal integer; there is
     *             no default value.  The logical byte size is not necessarily
     *             the same as the transfer byte size.  If there is a
     *             difference in byte sizes, then the logical bytes should be
     *             packed contiguously, disregarding transfer byte boundaries
     *             and with any necessary padding at the end.
     *
     *             When the data reaches the receiving host, it will be
     *             transformed in a manner dependent on the logical byte size
     *             and the particular host.  This transformation must be
     *             invertible (i.e., an identical file can be retrieved if the
     *             same parameters are used) and should be well publicized by
     *             the FTP implementors.
     *
     *             For example, a user sending 36-bit floating-point numbers to
     *             a host with a 32-bit word could send that data as Local byte
     *             with a logical byte size of 36.  The receiving host would
     *             then be expected to store the logical bytes so that they
     *             could be easily manipulated; in this example putting the
     *             36-bit logical bytes into 64-bit double words should
     *             suffice.
     *
     *             In another example, a pair of hosts with a 36-bit word size
     *             may send data to one another in words by using TYPE L 36.
     *             The data would be sent in the 8-bit transmission bytes
     *             packed so that 9 transmission bytes carried two host words.
     * @param size size of the local byte
     */
    public static RepresentationType local(int size) {
        return new RepresentationType("L", Integer.toString(size));
    }

    public static RepresentationType asciiNonPrint() {
        return new RepresentationType("A", "N");
    }

    public static RepresentationType asciiTelnet() {
        return new RepresentationType("A", "T");
    }

    public static RepresentationType asciiCarriageControl() {
        return new RepresentationType("A", "C");
    }

    public static RepresentationType custom(String type) {
        return custom(type, null);
    }

    public static RepresentationType custom(String type, String argument) {
        return new RepresentationType(type, argument);
    }

    public String getType() {
        return type;
    }

    public String getFormat() {
        return format;
    }
}
