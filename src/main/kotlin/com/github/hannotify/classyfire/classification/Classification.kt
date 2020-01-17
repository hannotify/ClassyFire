package com.github.hannotify.classyfire.classification

import com.github.hannotify.classyfire.data.category.Category
import com.github.hannotify.classyfire.data.transaction.Transaction

class Classification (val transaction: Transaction, val category: Category, val probability: Float)

