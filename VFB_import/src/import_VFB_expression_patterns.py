#!/usr/bin/env python

import sys
sys.path.append("../../../../build/")
import phisSchema
import pyxb

# Make an instance of the schema
image = phisSchema.Image()
channel = phisSchema.Channel()
roi = phisSchema.Roi()

# Each branch of the schema must be expanded via pyxb.BIND() to reveal attributes:
image.image_description = pyxb.BIND()
image.image_description.host_name = "Virtual Fly Brain"
image.image_description.host_url = "http://www.virtualflybrain.org"

# Check dimensions  - should these really be compulsory?
image.image_description.image_dimensions.image_width = 512
image.image_description.image_dimensions.image_height = 512
image.image_description.image_dimensions.image_width = 512


# imaging_method = image.image_description.imaging_method


# Attribute assignment is simple
 test.image_description.image_type = "expression"
