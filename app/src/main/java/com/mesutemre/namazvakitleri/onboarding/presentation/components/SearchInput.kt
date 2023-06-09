package com.mesutemre.namazvakitleri.onboarding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.mesutemre.namazvakitleri.R
import com.mesutemre.namazvakitleri.core.ext.sdp
import com.mesutemre.namazvakitleri.ui.theme.NamazvakitleriTheme

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    hint: String,
    text: String,
    onChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(NamazvakitleriTheme.shapes.medium)
            .background(color = NamazvakitleriTheme.colors.searchTextBackgroundColor)
            .border(
                width = 1.sdp,
                color = NamazvakitleriTheme.colors.searchTextBorderColor,
                shape = NamazvakitleriTheme.shapes.medium
            ),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = NamazvakitleriTheme.colors.searchTextColor,
            backgroundColor = NamazvakitleriTheme.colors.searchTextBackgroundColor
        ),
        placeholder = {
            Text(
                text = hint,
                style = NamazvakitleriTheme.typography.searchTextStyle,
                color = NamazvakitleriTheme.colors.searchTextHintColor
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = stringResource(id = R.string.common_search),
                tint = NamazvakitleriTheme.colors.searchTextColor
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
                    contentDescription = stringResource(id = R.string.common_clear),
                    tint = NamazvakitleriTheme.colors.searchTextColor,
                    modifier = Modifier.clickable {
                        //onClearSearch()
                    })
            }
        },
        textStyle = NamazvakitleriTheme.typography.searchTextStyle.copy(color = NamazvakitleriTheme.colors.searchTextColor),
        value = text,
        onValueChange = onChange
    )
}

/*modifier = modifier
            .fillMaxWidth()
            .height(42.sdp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorPalette.white)
            .border(
                width = 1.sdp,
                color = MaterialTheme.colorPalette.otherGrayLight,
                shape = MaterialTheme.shapes.medium
            ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = MaterialTheme.colorPalette.transparent
            )
        },
        trailingIcon = {
            if (searchInput.isNotEmpty()) {
                Icon(imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorPalette.transparent,
                    modifier = Modifier.rippleClick {
                        onClearSearch()
                    })
            }
        }*/