package com.project.sebastianrohe.twitter.services;

import com.project.sebastianrohe.twitter.data.Tweet;

import java.util.*;

/**
 * This class provides specific 'services' to get information about tweet data.
 *
 * @author Sebastian Rohe
 */
public class TweetService {

    /**
     * This method takes a set of users and tweets and fills user set with user strings of every tweet.
     *
     * @param tweetSet A set of tweet objects.
     * @return Set of all users from tweets.
     */
    public Set<String> getAllUsers(Set<Tweet> tweetSet) {
        Set<String> userSet = new HashSet<>();

        // For every tweet the user string gets added as element in user set.
        for (Tweet tweet : tweetSet) {
            userSet.add(tweet.getUser()); // User gets added to user set
        }
        // Return resulting user set.
        return userSet;
    }

    /**
     * Method to calculate the average length of tweet content.
     * Requires a set of tweets and a set of users.
     *
     * @param tweetSet A set of tweet objects.
     * @return Average length of tweet content as double value.
     */
    public double getAverageTweetLength(Set<Tweet> tweetSet) {
        // Sum of tweet contents is initialized with 0
        int sum = 0;

        // Iterating over every tweet from the tweet set and adding the length of every content string
        // to sum of all tweet contents.
        for (Tweet tweet : tweetSet) {
            sum += tweet.getContent().length(); // length of the content of every tweet is added to sum
        }
        // Divide the sum of tweet contents by the size of the tweet set (number of tweets in set).
        // Attention: To get exact result the size of tweetSet is cast to double.
        return sum / (double) tweetSet.size();
    }

    /**
     * Method to calculate the average word count in tweets. Requires a set of tweets.
     *
     * @param tweetSet A Set of tweet objects.
     * @return Average number of words in tweets.
     */
    public double getAverageNumberOfWords(Set<Tweet> tweetSet) {
        // Number of words for all tweets is 0 at the start.
        int numberOfWordsInAllTweets = 0;

        // For every tweet its content is split in an array of words.
        for (Tweet tweet : tweetSet) {
            String tweetContentString = tweet.getContent();
            // Tweet content strings are split with regex "[\\s+]" (blank space) to get an array of words.
            String[] words = tweetContentString.split("[\\s+]");
            // Length value of word array is added to numberOfWordsInAllTweets.
            numberOfWordsInAllTweets += words.length;
        }
        // Divide the number of words in all tweets by the size of the given tweet set and return the result.
        return numberOfWordsInAllTweets / (double) tweetSet.size();
    }

    /**
     * This method calculates the average number of hashtags of tweets.
     *
     * @param usedHashtagsList The list of used hashtags.
     * @param tweetSet         A set of tweet objects.
     * @return Average number of hashtags in tweets.
     */
    public double getAverageNumberOfHashtags(List<String> usedHashtagsList, Set<Tweet> tweetSet) {
        // Calculate average number of used hashtags and return it.
        return usedHashtagsList.size() / (double) tweetSet.size();
    }

    /**
     * Method to get all used hashtags as list of strings.
     *
     * @param tweetSet A Set of tweet objects.
     * @return List of strings representing hashtags.
     */
    public List<String> getAllHashtags(Set<Tweet> tweetSet) {
        // Create empty List of hashtags.
        List<String> usedHashtags = new ArrayList<>();
        Set<String> hashtagsStringSet;

        // Iterating for every tweet in tweetSet.
        for (Tweet tweet : tweetSet) {
            hashtagsStringSet = tweet.getHashtags();

           // Nested for loop to get every String from the string set and add it to list of used hashtags.
           for (String hashtagString : hashtagsStringSet) {
                usedHashtags.add(hashtagString);
            }
        }
        // Return resulting list of all hashtags.
        return usedHashtags;
    }


    /**
     * Method to get occurrences of all hashtags.
     *
     * @param listOfAllUsedHashtags The list of used hashtags in all tweets.
     * @return A Map of hashtag strings with corresponding occurrence values as integers.
     */
    public Map<String, Integer> getOccurrencesOfHashtags(List<String> listOfAllUsedHashtags) {
        // Hashmap to store the frequency of element.
        Map<String, Integer> hashtagMap = new HashMap<>();

        for (String hashtagString : listOfAllUsedHashtags) {
            Integer j = hashtagMap.get(hashtagString);
            hashtagMap.put(hashtagString, (j == null) ? 1 : j + 1);
        }
        return hashtagMap;
    }

    /**
     * Method to get all users with tweets above average content length.
     *
     * @param tweetSet A Set of tweet objects.
     * @return A set of users (in alphanumerical order) with long tweets.
     */
    public Set<String> getUsersTwitteringLongTweets(Set<Tweet> tweetSet) {
        // Using TreeSet to get alphanumerical order.
        Set<String> usersTwitteringLongTweets = new TreeSet<>();

        // Use method to get average tweet length.
        double averageTweetLength = getAverageTweetLength(tweetSet);

        for (Tweet tweet : tweetSet) {
            // Only add user if content of corresponding tweet is longer than average length.
            if (tweet.getContent().length() > averageTweetLength) {
                // In this case add user to set.
                usersTwitteringLongTweets.add(tweet.getUser());
            }
        }
        // Return set of strings representing users with long tweets.
        return usersTwitteringLongTweets;
    }

}
