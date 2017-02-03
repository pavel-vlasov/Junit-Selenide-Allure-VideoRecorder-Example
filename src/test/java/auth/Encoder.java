package auth;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;

import java.io.IOException;

/**
 * Created by vlas2 on 03.02.2017.
 */
public class Encoder {

        //encode("D:/google_test_study/video/" + "open_page_recording_2017_03_02_01_06_29.avi", "D:/google_test_study/video/" + "1.mp4"  );

    public static void encode (String input, String output) {
        FFmpeg ffmpeg = null;
        FFprobe ffprobe = null;
        FFmpegProbeResult probeResult = null;
        try {
            ffmpeg = new FFmpeg("C:/ffmpeg/bin/ffmpeg.exe");
            ffprobe = new FFprobe("C:/ffmpeg/bin/ffprobe.exe");
            probeResult = ffprobe.probe(input);
        } catch (IOException e) {
            e.printStackTrace();
        }


    FFmpegBuilder builder = new FFmpegBuilder()

            .setInput(probeResult)     // Filename, or a FFmpegProbeResult
            .overrideOutputFiles(true) // Override the output if it exists

            .addOutput(output)   // Filename for the destination
            .setFormat("mp4")        // Format is inferred from filename, or can be set
            .setTargetSize(1000_000)  // Aim for a 250KB file

            .disableSubtitle()       // No subtiles

            .setAudioChannels(1)         // Mono audio
            .setAudioCodec("aac")        // using the aac codec
            .setAudioSampleRate(48_000)  // at 48KHz
            .setAudioBitRate(32768)      // at 32 kbit/s

            .setVideoCodec("libx264")     // Video using x264
            .setVideoFrameRate(24, 1)     // at 24 frames per second
            .setVideoResolution(1920, 1080) // at 640x480 resolution

            .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
            .done();

    FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

// Run a one-pass encode
   executor.createJob(builder).run();

// Or run a two-pass encode (which is slower at the cost of better quality)
 //   executor.createTwoPassJob(builder).run();
}}
