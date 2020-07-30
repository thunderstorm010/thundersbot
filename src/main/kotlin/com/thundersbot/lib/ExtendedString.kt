package com.thundersbot.lib

open class ExtendedString {

    open fun String.startsWithAnyOf(prefixes: Array<String>, ignoreCase: Boolean = false): Boolean {
        for (prefix in prefixes) {
            if (ignoreCase) {
                if (this.startsWith(prefix,true)) {
                    return true
                }
            } else {
                if (this.startsWith(prefix)) {
                    return true
                }
            }
        }
        return false
    }

    open fun String.containsAnyOf(prefixes: Array<String>, ignoreCase: Boolean = false): Boolean {
        for (prefix in prefixes) {
            if (ignoreCase) {
                if (this.contains(prefix,true)) {
                    return true
                }
            } else {
                if (this.contains(prefix)) {
                    return true
                }
            }
        }
        return false
    }

    open fun String.equalsAnyOf(prefixes: Array<String>, ignoreCase: Boolean = false): Boolean {
        for (prefix in prefixes) {
            if (ignoreCase) {
                if (this.equals(prefix,true)) {
                    return true
                }
            } else {
                if (this.equals(prefix)) {
                    return true
                }
            }
        }
        return false
    }


}

