/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package storage;

import java.util.Arrays;
import java.util.List;

/**
 * Util containing various text related utils.
 */
public final class HealthHelpTextUtil {

    private HealthHelpTextUtil() {
    }

    public static final String COMPLETE_HELP =
            "If you are an new patient and need a regular visit,please tell me your firstName and date of birth and your Insurance details.";
	public static final String NEXT_HELP = "Do you still need help with something else";
	
    /**
     * Cleans up the player name, and sanitizes it against the blacklist.
     *
     * @param recognizedPlayerName
     * @return
     */
    public static String getPatientName(String recognizedPatientName) {
        if (recognizedPatientName == null || recognizedPatientName.isEmpty()) {
            return null;
        }

        String cleanedName;
        if (recognizedPatientName.contains(" ")) {
            // the name should only contain a first name, so ignore the second part if any
            cleanedName = recognizedPatientName.substring(recognizedPatientName.indexOf(" "));
        } else {
            cleanedName = recognizedPatientName;
        }

        
        return cleanedName;
    }
}
