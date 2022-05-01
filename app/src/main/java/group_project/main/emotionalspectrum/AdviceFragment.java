package group_project.main.emotionalspectrum;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AdviceFragment extends DialogFragment {
    Record currentRecord;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String currentEmotion = currentRecord.getEmotionName();

        String[] guiltAdvices = new String[] {
                "If you find yourself only focusing on negative thoughts, consider ways to think differently about the situation. Were there other factors that played a role? What can you do differently in the future? Finding a way to shift your focus from the negative to more realistic, positive thoughts may help you move past your feelings of self-recrimination.",
                "Learning how to practice self-forgiveness can be an important tool for letting go of guilt. Forgiving yourself doesn't mean letting yourself off the hook if you've made a mistake or caused someone harm; instead, it's about taking responsibility, allowing yourself some time to express remorse, making amends, and then finding a way to move on.",
                "Sharing your feelings with a close friend can sometimes be helpful. Social support can play a pivotal role in coping with difficult emotions, so maintaining your relationships with friends and loved ones is important.",
                "If you struggle to talk to your loved ones about your feelings of guilt or if they are not providing the type of support you need, discussing your feelings with a mental health professional can also be helpful. Traditional face-to-face therapy sessions are one option, but online therapy may also be a convenient option that you might want to consider."
        };

        String[] fearAdvices = new String[] {
                "If you’re scared of activities that you know roughly what to expect such as job interviews, going to the dentist and confronting people, the best way to overcome it is to prepare. Practicing or thinking through what is going to happen prior to the event itself removes much of the uncertainty that causes fear.",
                "Fear stems from a lack of control so focusing on things that you can control can help reduce your feelings. If you’re fearful of something major that you don’t have much control over, find one small thing that you can take action on and focus your attention on that so fear doesn’t overwhelm you.",
                "Research shows that it is easier to let go of your fear by engaging in a self-soothing behavior that relaxes you than it is to try to talk yourself out of being afraid. Find the self-soothing behavior that distracts you from your fear whether it’s prayer, meditation, yoga, a hobby etc. and put more time into this practice when you’re afraid."
        };

        String[] angerAdvices = new String[] {
                "In the heat of the moment, it's easy to say something you'll later regret. Take a few moments to collect your thoughts before saying anything. Also allow others involved in the situation to do the same.",
                "As soon as you're thinking clearly, express your frustration in an assertive but nonconfrontational way. State your concerns and needs clearly and directly, without hurting others or trying to control them.",
                "Physical activity can help reduce stress that can cause you to become angry. If you feel your anger escalating, go for a brisk walk or run. Or spend some time doing other enjoyable physical activities.",
                "Give yourself short breaks during times of the day that tend to be stressful. A few moments of quiet time might help you feel better prepared to handle what's ahead without getting irritated or angry.",
                "Instead of focusing on what made you mad, work on resolving the issue at hand. Does your child's messy room make you upset? Close the door. Is your partner late for dinner every night? Schedule meals later in the evening. Or agree to eat on your own a few times a week. Also, understand that some things are simply out of your control. Try to be realistic about what you can and cannot change. Remind yourself that anger won't fix anything and might only make it worse.",
                "Criticizing or placing blame might only increase tension. Instead, use \"I\" statements to describe the problem. Be respectful and specific. For example, say, \"I'm upset that you left the table without offering to help with the dishes\" instead of \"You never do any housework.\"",
                "Forgiveness is a powerful tool. If you allow anger and other negative feelings to crowd out positive feelings, you might find yourself swallowed up by your own bitterness or sense of injustice. Forgiving someone who angered you might help you both learn from the situation and strengthen your relationship.",
                "Lightening up can help diffuse tension. Use humor to help you face what's making you angry and, possibly, any unrealistic expectations you have for how things should go. Avoid sarcasm, though — it can hurt feelings and make things worse.",
                "When your temper flares, put relaxation skills to work. Practice deep-breathing exercises, imagine a relaxing scene, or repeat a calming word or phrase, such as \"Take it easy.\" You might also listen to music, write in a journal or do a few yoga poses — whatever it takes to encourage relaxation.",
                "Learning to control anger can be a challenge at times. Seek help for anger issues if your anger seems out of control, causes you to do things you regret or hurts those around you."
        };

        String[] shameAdvices = new String[] {
                "To move past shame, start by developing some compassion for yourself. We are often much harder on ourselves than we are on anyone else. In fact, if we treated others the way we treat ourselves, we’d probably be ostracized or locked up.",
                "Try to regard yourself the way you would a friend. Imagine a friend telling you they were ashamed of whatever it was that you did, or whatever happened to you. Imagine reacting with compassion, knowing that although your friend isn’t perfect, they deserve to be happy. Try extending that same feeling to yourself.",
                "Try opening up about shame. This is what really allows you to heal. As noted above, shame wants to hide but that only makes it worse. If you don’t yet feel like you can open up to someone you trust and care about, consider opening up in therapy."
        };

        String[] sufferingAdvices = new String[] {
                "Try relaxation techniques. There are many different types of relaxation techniques that can be helpful during the time of emotional pain and stress. These include using simple breathing techniques, meditation or mindfulness activities or it could even include grounding exercises, which are helpful in situations of trauma or acute stress.",
                "Use your creativity. This could be drawing, knitting, art, dance, photography, music, pottery, or really any activity that you enjoy. Music can help you drain your emotion out.",
                "Write in a journal. Writing can be very powerful and help to actually release the emotions. ",
                "Exercise. Physical exercise is a key component of mind-body wellness. Movement can allow for our negative emotions to become unstuck and actually move; therefore, allowing us to process these emotions and release them. So, do not discount the importance of going for a walk or going to the gym.",
                "Distract yourself. There are times when our emotional state can be overwhelming and difficult to manage. This is when having a distraction could be helpful with coping. A distraction can be something as simple as watching a movie or having coffee with a friend.",
                "Learn to identify unhealthy patterns with your thinking, such as rumination or negative thinking and work toward changing those patterns. For example, when you notice yourself engaging in negative thinking or ruminating over the bad situation that happened, use self-talk to change that pattern. You can say things like, “I will get through this,” “I am strong” or “I am brave.” First, you must identify when we are not thinking in a healthy manner and then work to change that."
        };

        String[] disguiseAdvices = new String[] {
                "Try to practice mindfulness. Mindfulness refers to your awareness of the present moment and ability to experience things as they happen. Emotional mindfulness means acknowledging and accepting feelings as they come up, even if you choose not to express them immediately.",
                "Share your feelings honestly. Your emotions are part of your life experience. Discounting them can eventually invalidate your identity and sense of self, and prevent you from achieving personal goals. There are ways to share feelings, even negative ones, without being rude. It helps to practice emotional communication by first opening up to loved ones and others you trust.",
                "Talk to someone you trust. If you don’t get a chance to express your emotions, talking about them later can still help, particularly if you can’t change the circumstances."
        };

        String[] contemptAdvices = new String[] {
                "There are several ways to reshape feelings of contempt into healthy means of communication. Instead of using phrases like \"you always\" or \"you never\", try statements that directly address how you're feeling—rather than what they're doing wrong. \"I'm stressed about the chores, and I'd love your help tackling them so we can move on with our day,\" is a constructive response to annoyances around the house.",
                "During intense disagreements, people tend to interrupt or think about their responses while their partners are talking. I suggest listening without interrupting and empathizing with your partner to validate their emotions. Everyone is entitled to their own feelings, so do your best not to criticize, judge, belittle your partner, or minimize the importance of their feelings",
                "Contempt is a powerful feeling that can absolutely be worked through, go away, and be resolved when dealt with openly, honestly, and directly. When it's especially-troubling, it is better to reach out to a third party like a therapist, religious leader, relationship counselor, or psychologist for advice."
        };

        String[] anticipationAdvices = new String[] {
                "If you find your anticipation anxiety too much, you might benefit from some of the following activities to control this emotion: deep breathing, progressive muscle relaxation, counting objects around the room"
        };

        String[] prideAdvices = new String[] {
                "Prideful thinking can be interpreted as “your way or the highway.” By staying open-minded, you’ll already be serving yourself and team members better.",
                "If you feel yourself shutting someone down before someone even finishes a sentence, take a breath and make yourself focus on their opinion. If you don’t agree or understand, ask a clarifying question. Being open-minded will boost team morale and you’ll also wind up with better ideas and results.",
                "Listening is the antithesis to pride because when you’re giving someone else the opportunity to express themselves or their ideas, you’re putting your pride aside. Focus more on listening instead of talking and see how many new things you learn in the process.",
                "Whenever you feel yourself becoming stubborn, argumentative, or defensive (symptoms of pride), ask yourself this one question: “Will this help me become better at my job, move up in the company, or improve the business’s bottom line?” If it’s a no or even not an immediate yes, then you know you have pride to contend with and let go."
        };

        String[] joyAdvices = new String[] {
                "Joy predicts lower heart rate and blood pressure",
                "Joy strengthens your immune system",
                "Joy combats disease and disability. Happy people have fewer aches and pains"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        if (currentEmotion.equals("guilt")) {
            int n = (int)Math.floor(Math.random() * guiltAdvices.length);
            builder.setMessage(guiltAdvices[n]);
        }

        else if (currentEmotion.equals("fear")) {
            int n = (int)Math.floor(Math.random() * fearAdvices.length);
            builder.setMessage(fearAdvices[n]);
        }

        else if (currentEmotion.equals("anger")) {
            int n = (int)Math.floor(Math.random() * angerAdvices.length);
            builder.setMessage(angerAdvices[n]);
        }

        else if (currentEmotion.equals("shame")) {
            int n = (int)Math.floor(Math.random() * shameAdvices.length);
            builder.setMessage(shameAdvices[n]);
        }

        else if (currentEmotion.equals("suffering")) {
            int n = (int)Math.floor(Math.random() * sufferingAdvices.length);
            builder.setMessage(sufferingAdvices[n]);
        }

        else if (currentEmotion.equals("disguise")) {
            int n = (int)Math.floor(Math.random() * disguiseAdvices.length);
            builder.setMessage(disguiseAdvices[n]);
        }

        else if (currentEmotion.equals("contempt")) {
            int n = (int)Math.floor(Math.random() * contemptAdvices.length);
            builder.setMessage(contemptAdvices[n]);
        }

        else if (currentEmotion.equals("anticipation")) {
            int n = (int)Math.floor(Math.random() * anticipationAdvices.length);
            builder.setMessage(anticipationAdvices[n]);
        }

        else if (currentEmotion.equals("pride")) {
            int n = (int)Math.floor(Math.random() * prideAdvices.length);
            builder.setMessage(prideAdvices[n]);
        }

        else if (currentEmotion.equals("joy")) {
            int n = (int)Math.floor(Math.random() * joyAdvices.length);
            builder.setMessage(joyAdvices[n]);
        }

        builder.setPositiveButton(R.string.agree, (dialog, id) -> {
        });

        builder.create();
        return builder.create();
    }
}
