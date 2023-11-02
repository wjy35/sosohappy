from django.db import models


class Category(models.Model):
    category_id = models.BigAutoField(primary_key=True)
    category_image = models.TextField(blank=True, null=True)
    category_name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'category'


class CategoryPick(models.Model):
    pick_id = models.BigAutoField(primary_key=True)
    member_id = models.BigIntegerField(blank=True, null=True)
    pick_count = models.IntegerField()
    pick_time = models.DateTimeField(blank=True, null=True)
    category = models.ForeignKey(Category, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'category_pick'
