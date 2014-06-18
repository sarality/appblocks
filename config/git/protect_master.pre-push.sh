#!/bin/bash

# Prevents a push directly to master. 
#
# Author : abhideep@ (Abhideep Singh)


# Check if we actually have commits to push
commits=`git log @{u}..`
if [ -z "$commits" ]; then
    exit 0
fi

# Check if we are pushing to a protected branch
protected_branch='master'
# Look for last token after / in ref/head.master to get current branch
current_branch=$(git symbolic-ref HEAD | sed -e 's,.*/\(.*\),\1,')

if [[ $current_branch = $protected_branch ]]
then
    echo "WARNING!!!! You are trying to push to the master"
    echo "Please send a push request and then merge with master"
    echo "In the rare situation that you do need to push the master"
    echo "Use: git push --no-verify"
    exit 1
fi
exit 0

